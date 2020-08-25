import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SuivEnergiesTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { TD015ProductionEnergiesDeleteDialogComponent } from 'app/entities/td-015-production-energies/td-015-production-energies-delete-dialog.component';
import { TD015ProductionEnergiesService } from 'app/entities/td-015-production-energies/td-015-production-energies.service';

describe('Component Tests', () => {
  describe('TD015ProductionEnergies Management Delete Component', () => {
    let comp: TD015ProductionEnergiesDeleteDialogComponent;
    let fixture: ComponentFixture<TD015ProductionEnergiesDeleteDialogComponent>;
    let service: TD015ProductionEnergiesService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD015ProductionEnergiesDeleteDialogComponent],
      })
        .overrideTemplate(TD015ProductionEnergiesDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD015ProductionEnergiesDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD015ProductionEnergiesService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
