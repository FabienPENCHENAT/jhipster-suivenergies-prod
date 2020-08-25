import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD012GenerateurChauffageDetailComponent } from 'app/entities/td-012-generateur-chauffage/td-012-generateur-chauffage-detail.component';
import { TD012GenerateurChauffage } from 'app/shared/model/td-012-generateur-chauffage.model';

describe('Component Tests', () => {
  describe('TD012GenerateurChauffage Management Detail Component', () => {
    let comp: TD012GenerateurChauffageDetailComponent;
    let fixture: ComponentFixture<TD012GenerateurChauffageDetailComponent>;
    const route = ({ data: of({ tD012GenerateurChauffage: new TD012GenerateurChauffage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD012GenerateurChauffageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TD012GenerateurChauffageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD012GenerateurChauffageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tD012GenerateurChauffage on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tD012GenerateurChauffage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
