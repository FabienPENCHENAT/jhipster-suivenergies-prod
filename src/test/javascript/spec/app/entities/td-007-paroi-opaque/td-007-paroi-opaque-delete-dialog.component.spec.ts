import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SuivEnergiesTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { TD007ParoiOpaqueDeleteDialogComponent } from 'app/entities/td-007-paroi-opaque/td-007-paroi-opaque-delete-dialog.component';
import { TD007ParoiOpaqueService } from 'app/entities/td-007-paroi-opaque/td-007-paroi-opaque.service';

describe('Component Tests', () => {
  describe('TD007ParoiOpaque Management Delete Component', () => {
    let comp: TD007ParoiOpaqueDeleteDialogComponent;
    let fixture: ComponentFixture<TD007ParoiOpaqueDeleteDialogComponent>;
    let service: TD007ParoiOpaqueService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD007ParoiOpaqueDeleteDialogComponent],
      })
        .overrideTemplate(TD007ParoiOpaqueDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD007ParoiOpaqueDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD007ParoiOpaqueService);
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
